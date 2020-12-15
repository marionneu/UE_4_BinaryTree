package at.fhv.mne8893.binarytree;

public  class  TreeNode<T> {
    private final T _value;
    private TreeNode<T> _childLeft;
    private TreeNode<T> _childRight;

    public TreeNode(T value){
        _value = value;
    }

    public T getValue() {
        return _value;
    }

    public TreeNode<T> getChildLeft() {
        return _childLeft;
    }

    public void setChildLeft(TreeNode<T> childLeft) {

        _childLeft = childLeft;
    }

    public TreeNode<T> getChildRight() {
        return _childRight;
    }

    public void setChildRight(TreeNode<T> childRight) {
        _childRight = childRight;
    }

    public boolean isLeave(){
        return (_childLeft == null && _childRight == null);
    }

    public int calculateHeight() {
        return calculateHeight(this, 0);
    }

    public int calculateLeaves() {
        return calculateLeaves(this);
    }

    private static <T> int calculateHeight(TreeNode<T> toCount, int height) {
        height++;
        if (toCount.isLeave())
            return height;

        int heightLeft = 0;
        int heightRight = 0;
        if (toCount._childRight != null)
            heightRight = calculateHeight(toCount._childRight, height);
        if (toCount._childLeft != null)
            heightLeft = calculateHeight(toCount._childLeft, height);

        return Math.max(heightLeft, heightRight);
    }

    private static <T> int calculateLeaves(TreeNode<T> toCount) {
        int count = 0;
        if (toCount.isLeave())
            return 1;

        if (toCount._childRight != null)
            count += calculateLeaves(toCount._childRight);
        if (toCount._childLeft != null)
            count += calculateLeaves(toCount._childLeft);

        return count;
    }
}
