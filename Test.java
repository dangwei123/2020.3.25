在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。

每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。

请你返回最终形体的表面积。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/surface-area-of-3d-shapes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int surfaceArea(int[][] grid) {
        int res=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                int tmp=grid[i][j];
                if(tmp!=0){
                    res+=2+(tmp<<2);
                }
                if(i-1>=0&&grid[i-1][j]!=0){
                    res-=(Math.min(grid[i-1][j],tmp)<<1);
                }
                if(j-1>=0&&grid[i][j-1]!=0){
                    res-=(Math.min(grid[i][j-1],tmp)<<1);
                }
            }
        }
        return res;
    }
}

您将获得一个双向链表，除了下一个和前一个指针之外，它还有一个子指针，可能指向单独的双向链表。这些子列表可能有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。

扁平化列表，使所有结点出现在单级双链表中。您将获得列表第一级的头部。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
    
    
   
   public Node flatten(Node head) {
       if(head==null) return null;
        Node pre=new Node(-1,null,null,null);
        Node res=dfs(pre,head);
        pre.next.prev=null;
        return pre.next;
    }
    private Node dfs(Node pre,Node cur){
        if(cur==null){
            return pre;
        }
        Node next=cur.next;
        pre.next=cur;
        cur.prev=pre;
        
        Node tmp=dfs(cur,cur.child);
        cur.child=null;
        
        return dfs(tmp,next);
    }
}

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/
class Solution {
    public Node flatten(Node head) {
        if(head==null){
            return null;
        }
        Stack<Node> stack=new Stack<>();
        Node dummy=new Node(-1,null,null,null);
        Node pre=dummy;
        stack.push(head);
        while(!stack.isEmpty()){
            Node cur=stack.pop();
            pre.next=cur;
            cur.prev=pre;
            pre=cur;

            if(cur.next!=null){
                stack.push(cur.next);
            }
            if(cur.child!=null){
                stack.push(cur.child);
                cur.child=null;
            }
        }
        dummy.next.prev=null;
        return dummy.next;
    }
}

给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

要求返回这个链表的 深拷贝。 
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if(head==null) return null;
        Node cur=head;
        while(cur!=null){
            Node node=new Node(cur.val);
            node.next=cur.next;
            cur.next=node;
            cur=cur.next.next;
        }
        cur=head;
        while(cur!=null){
            if(cur.random!=null)
               cur.next.random=cur.random.next;
            cur=cur.next.next;
        }
        cur=head;
        Node newHead=cur.next;
        while(cur.next!=null){
            Node next=cur.next;
            cur.next=cur.next.next;
            cur=next;
        }
        return newHead;
    }
}

给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(k<=0||head==null){
            return head;
        }
        int len=0;
        ListNode cur=head;
        while(cur.next!=null){
            len++;
            cur=cur.next;
        }
        cur.next=head;
        len++;
        k%=len;
        cur=head;
        k=len-k-1;
        while(k--!=0){
            cur=cur.next;
        }
        ListNode next=cur.next;
        cur.next=null;
        return next;
    }
}

