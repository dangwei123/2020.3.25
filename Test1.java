给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root,0,sum);
    }
    private boolean dfs(TreeNode root,int sum,int target){
        if(root==null){
            return false;
        }
        sum+=root.val;
        if(root.left==null&&root.right==null&&sum==target){
            return true;
        }
        if(dfs(root.left,sum,target)||dfs(root.right,sum,target)){
            return true;
        }
        return false;
    }
}

根据一棵树的前序遍历与中序遍历构造二叉树。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int index;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return build(preorder,inorder,0,inorder.length-1,map);
    }
    private TreeNode build(int[] preorder,int[] inorder,int left,int right,Map<Integer,Integer>map){
        if(index>=inorder.length||left>right){
            return null;
        }
        TreeNode root=new TreeNode(preorder[index]);
        int inRoot=map.get(preorder[index++]);
        root.left=build(preorder,inorder,left,inRoot-1,map);
        root.right=build(preorder,inorder,inRoot+1,right,map);
        return root;
    }
}
根据一棵树的中序遍历与后序遍历构造二叉树。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int index;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        index=inorder.length-1;
        return build(inorder,postorder,0,inorder.length-1,map);
    }
    private TreeNode build(int[] inorder,int[] postorder,int left,int right,Map<Integer,Integer> map){
        if(index<0||left>right){
            return null;
        }
        TreeNode root=new TreeNode(postorder[index]);
        int inRoot=map.get(postorder[index--]);
        root.right=build(inorder,postorder,inRoot+1,right,map);
        root.left=build(inorder,postorder,left,inRoot-1,map);
        return root;
    }
}

给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node root) {
        if(root==null) return null;
        Queue<Node> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            while(size!=0){
                Node cur=queue.poll();
                if(size==1){
                    cur.next=null;
                }else{
                    cur.next=queue.peek();
                }
                if(cur.left!=null) queue.offer(cur.left);
                if(cur.right!=null) queue.offer(cur.right);
                size--;
            }
        }
        return root;
    }
}

给定一个二叉树，填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node root) {
        dfs(root);
        return root;
    }
    private void dfs(Node root){
        if(root==null){
            return;
        }
        if(root.left!=null){
            if(root.right!=null){
                root.left.next=root.right;
            }else{
                root.left.next=getNext(root.next);
            }
            
        }
        if(root.right!=null){
            root.right.next=getNext(root.next);
        }
        dfs(root.right);
        dfs(root.left);
    }
    private Node getNext(Node root){
        if(root==null) return null;
        if(root.left!=null) return root.left;
        if(root.right!=null) return root.right;
        if(root.next!=null) return getNext(root.next);
        return null;
    }
}

