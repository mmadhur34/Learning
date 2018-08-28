package com.home.amazon;

import org.junit.Test;

public class BSTDistance {
    class TreeNode<T extends Comparable>{
        T val;
        TreeNode<T> left;
        TreeNode<T> right;
        TreeNode(){}
        TreeNode(T val){
            this.val=val;
        }

        void addNode(TreeNode<T> root,T val){
            if(root==null)
                root= new TreeNode<>(val);
            else if(root.val.compareTo(val)>0){
                if(root.getLeft()==null){
                    root.setLeft(new TreeNode<>(val));
                }else
                addNode(root.getLeft(),val);
            }else{
                if(root.getRight()==null){
                    root.setRight(new TreeNode<>(val));
                }else
                addNode(root.getRight(),val);
            }
        }
        public TreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        public TreeNode<T> getRight() {
            return right;
        }

        public void setRight(TreeNode<T> right) {
            this.right = right;
        }
        public String toString(){
            if(this.val==null)
                return null;
            StringBuilder result= new StringBuilder("[");
            if(this.getLeft()!=null)
                result.append(this.getLeft().toString());
            result.append(val);
            if(this.getRight()!=null)
                result.append(this.getRight().toString());
            result.append(']');
            return result.toString();
        }
    }
    public int getDistanceFromRoot(TreeNode<Integer> root,int n){
        int count =0;
        if(root.val==null)return -1;
        if(root.val==n)return 0;
        else if(root.val<n)
            count= getDistanceFromRoot(root.getRight(),n)+1;
        else if(root.val>n)
            count= getDistanceFromRoot(root.getLeft(),n)+1;
        return count;
    }
    public int getDistance(TreeNode<Integer> root,int s1,int s2){
        if(root!=null){
            if(root.val>s1 && root.val>s2){
                //in left subtree
                return getDistance(root.getLeft(),s1,s2);
            }
            else if(root.val<s1 && root.val<s2){
                //in right sub tree
                return getDistance(root.getRight(),s1,s2);
            }
            else if((root.val>s1 && root.val<s2)||(root.val<s1 && root.val>s2)){
                int first = getDistanceFromRoot(root,s1);
                int second = getDistanceFromRoot(root,s2);
                if(first!=1 && second!=-1)
                    return first+second;
                else
                    return -1;


            }
        }

        return -1;
    }
    @Test
    public void testGetDistance(){
        TreeNode<Integer> tree = new TreeNode<>(5);
        tree.addNode(tree,3);
        tree.addNode(tree,6);
        tree.addNode(tree,1);
        tree.addNode(tree,4);
        tree.addNode(tree,2);
        System.out.println(tree);

        System.out.println("Distance found is "+getDistance(tree,2,4));

    }
}

