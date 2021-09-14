
import java.io.*;
import java.util.*;

public class isBalanced_travel_nd_solve {
  public static class Node {
    int data;
    Node left;
    Node right;

    Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Pair rtp = new Pair(root, 1);

    Stack<Pair> st = new Stack<>();
    st.push(rtp);

    int idx = 0;
    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == 1) {
        idx++;
        if (arr[idx] != null) {
          top.node.left = new Node(arr[idx], null, null);
          Pair lp = new Pair(top.node.left, 1);
          st.push(lp);
        } else {
          top.node.left = null;
        }

        top.state++;
      } else if (top.state == 2) {
        idx++;
        if (arr[idx] != null) {
          top.node.right = new Node(arr[idx], null, null);
          Pair rp = new Pair(top.node.right, 1);
          st.push(rp);
        } else {
          top.node.right = null;
        }

        top.state++;
      } else {
        st.pop();
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null) {
      return;
    }

    String str = "";
    str += node.left == null ? "." : node.left.data + "";
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data + "";
    System.out.println(str);

    display(node.left);
    display(node.right);
  }
  
  static class bpair{
    int height;
    boolean isbalanced;
      
  }
  
  public static bpair Isbalanced1(Node node){
      if(node==null){
          bpair bp=new bpair();
          bp.height=-1;
          bp.isbalanced=true;
          return bp;
      }
      bpair left=Isbalanced1(node.left);
      bpair right=Isbalanced1(node.right);
      
      
      bpair self=new bpair();
      self.height=Math.max(left.height,right.height)+1;
      self.isbalanced=(left.isbalanced)&&(right.isbalanced)&&(Math.abs(left.height-right.height)<=1);
      return self;
      
  }
  
  static boolean isbal=true;
  
  public static int height(Node node){
      if(node==null){
          return 0;
      }
      
      int lh=height(node.left);
      int rh=height(node.right);
      if(Math.abs(lh-rh)<=1){
          isbal=false;
        //   break;
      }
      
      int mh=Math.max(lh,rh)+1;
      return mh;
  }
  
  
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    Integer[] arr = new Integer[n];
    String[] values = br.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      if (values[i].equals("n") == false) {
        arr[i] = Integer.parseInt(values[i]);
      } else {
        arr[i] = null;
      }
    }

    Node root = construct(arr);
    // bpair ans=Isbalanced1(root);
    // System.out.println(ans.isbalanced);
    
    height(root);
    System.out.println(isbal);
    // write your code here
  }

}