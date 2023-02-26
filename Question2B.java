////2.b //You are given an array of binary trees that represent different cities
//// where a certain corporation has its branch office
//// and the organization wishes to provide service by constructing a service center.
//// Building service centers at any node, i.e.,
//// a city can give service to its directly connected cities where it can provide service to its parents,
//// itself, and its immediate children. Returns the smallest number of service centers
//// required by the corporation to provide service to all connected cities.
//// Note that: the root node represents the head office and other connected nodes represent the branch office
//// connected to the head office maintaining some kind of hierarchy.
//
//import javax.swing.tree.TreeNode;
//
//public class Cities {
//    int res = 0;
//    public int minCameraCover(TreeNode root) {
//        return (dfs(root) < 1 ? 1 : 0) + res;
//    }
//
//    public int dfs(TreeNode root) {
//        if (root == null) return 2;
//        int left = dfs(root.left), right = dfs(root.right);
//        if (left == 0 || right == 0) {
//            res++;
//            return 1;
//        }
//        return left == 1 || right == 1 ? 2 : 0;
//    }
//}


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Question2B {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res=new ArrayList<>();

        List<int[]> heights=new ArrayList<>();

        transformBuilding(buildings,heights);

        //if heights of 2 points are same then place the point with smaller height first else place point with smaller starting point

        Collections.sort(heights,(a, b)->(a[0]==b[0]) ? a[1]-b[1] : a[0]-b[0]);// TC->O(nlog n)

        PriorityQueue<Integer> pq=new PriorityQueue<Integer>((a, b)->(b-a));

        //seeding the Priority Queue
        pq.add(0);
        int prevMax=0;

        for(int[] height:heights){ //O(n)

            if(height[1]<0){
                pq.add(-height[1]);
            }
            else{
                pq.remove(height[1]); //O(log n)
            }

            int CurrentMax=pq.peek();
            if(CurrentMax!=prevMax)
            {
                List<Integer> subResult=new ArrayList<>();
                subResult.add(height[0]);
                subResult.add(CurrentMax);

                res.add(subResult);
                prevMax=CurrentMax;
            }
        }

        return res;
    }
    //this will seperate the values of start point and end point with height
    //example-->[1,2,3]-->[1,-3] & [2,3]-->here -(minus) is just for convention for starting point
    private void transformBuilding(int[][] buildings,List<int[]> heights)
    {
        for(int[] building:buildings)
        {
            heights.add(new int[]{building[0],-building[2]});
            heights.add(new int[]{building[1],building[2]});
        }



    }



}
