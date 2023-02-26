package BatchProcessingGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class BatchProcessingGUI extends JFrame {
    private JPanel taskPanel;
    private JButton addTaskButton, removeTaskButton,executeButton;
    private JTextArea logArea;
    private HashMap<String, ArrayList<String>> taskGraph;

    public BatchProcessingGUI(){
        super("Database Operation Batch Processing");


        //initialize task panel

        taskPanel=new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        JScrollPane taskScrollPane = new JScrollPane(taskPanel);

        //initialize the buttons
        addTaskButton=new JButton("Add Task");
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTaskDialog();
            }
        }
        );
        removeTaskButton=new JButton("Remove Task");
        removeTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTaskDialog();
            }
        });
        executeButton = new JButton("Execute Tasks");
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeTasks();
            }
        });
        //Initialize log area
        logArea=new JTextArea();
        JScrollPane logScrollPane=new JScrollPane(logArea);

        // Initialize frame layout
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1,3));
        controlPanel.add(addTaskButton);
        controlPanel.add(removeTaskButton);
        controlPanel.add(removeTaskButton);
        controlPanel.add(executeButton);

        setLayout(new BorderLayout());
        add(taskScrollPane, BorderLayout.CENTER);
        add(controlPanel,BorderLayout.NORTH);
        add(controlPanel,BorderLayout.SOUTH);

        //Initialize task graph
        taskGraph=new HashMap<String,ArrayList<String>>();

        //set frame properties
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addTaskDialog(){
        JDialog addTaskDialog=new JDialog(this,"Add Task",true);
        addTaskDialog.setLayout(new GridLayout(2,2));

        JLabel nameLabel = new JLabel("Task Name");
        JTextField nameField = new JTextField();

        JLabel dependentLabel=new JLabel("Dependent Task:");
        JComboBox dependentBox = new JComboBox();
        dependentBox.addItem("");

        Set<String> taskNames=taskGraph.keySet();
        for(String taskName:taskNames){
            dependentBox.addItem(taskName);
        }

        JButton addButton =new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName=nameField.getText();
                String dependentName=(String)dependentBox.getSelectedItem();

                if(taskName!=null&& !taskName.isEmpty()){
                    if(!taskGraph.containsKey(taskName)){
                        taskGraph.put(taskName, new ArrayList<String>());
                    }
                    if(dependentName!=null && !dependentName.isEmpty()){
                        taskGraph.get(dependentName).add(taskName);
                    }

                    taskPanel.add(new JLabel(taskName));
                    taskPanel.revalidate();
                    taskPanel.repaint();
                }
                addTaskDialog.dispose();
            }
        });
        addTaskDialog.add(nameLabel);
        addTaskDialog.add(nameField);
        addTaskDialog.add(dependentLabel);
        addTaskDialog.add(dependentBox);
        addTaskDialog.add(addButton);

        addTaskDialog.setSize(300,150);
        addTaskDialog.setLocationRelativeTo(this);
        addTaskDialog.setVisible(true);


    }

    private void removeTaskDialog(){
        JDialog removeTaskDialog = new JDialog(this,"remove Task",true);
        removeTaskDialog.setLayout(new GridLayout(2,1));

        JLabel removeLabel=new JLabel("Select a task to remove:");
        JComboBox removeBox=new JComboBox();
        Set<String> taskNames=taskGraph.keySet();
        for(String taskName:taskNames){
            removeBox.addItem(taskName);
        }
        JButton removeButton=new JButton("Remove Task");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName=(String)removeBox.getSelectedItem();
                if(taskName != null && !taskName.isEmpty()){
                    taskGraph.remove(taskName);
                    removeBox.removeItem(taskName);
                    taskPanel.remove(getTaskIndex(taskName));
                    taskPanel.revalidate();
                    taskPanel.repaint();
                }
                removeTaskDialog.dispose();
            }
        });
        removeTaskDialog.add(removeLabel);
        removeTaskDialog.add(removeBox);
        removeTaskDialog.add(removeButton);

        removeTaskDialog.setSize(300,150);
        removeTaskDialog.setLocationRelativeTo(this);
        removeTaskDialog.setVisible(true);
    }
    private void executeTasks(){
        //execute tasks in topological order
        ArrayList<String> taskOrder = new ArrayList<String>();
        HashSet<String> visited= new HashSet<String>();
        for(String taskName:taskGraph.keySet()){
        visit(taskName,visited,taskOrder);
        }
        logArea.setText("");
        for(String taskName:taskOrder){
            logArea.append("Executing task"+taskName+"...\n");


            //Excute Sql procedure or function here

        }
    }
    private void visit(String taskName, HashSet<String>visited, ArrayList<String> taskOrder){
        if(!visited.contains(taskName)){
            visited.add(taskName);
            for(String dependentName:taskGraph.get(taskName)){
                visit(dependentName,visited, taskOrder);
            }
            taskOrder.add(taskName);
        }
    }
    private int getTaskIndex(String taskName){
        int index=-1;
        Component[] components=taskPanel.getComponents();
        for(int i=0;i<components.length;i++){
            Component component=components[i];
            if(component instanceof JLabel&& ((JLabel)component).getText().equals(taskName)){
                index=i;
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        new BatchProcessingGUI();
    }
}
