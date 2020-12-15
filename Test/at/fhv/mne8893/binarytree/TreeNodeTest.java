package at.fhv.mne8893.binarytree;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class TreeNodeTest {

    @Test
   void is_Leave_False_WhenBothChildrenAreSet(){
        //Arrange
        //TreeNode<Integer> underTest = new TreeNode<>(10);
        var underTest = new TreeNode<>(0);
        underTest.setChildLeft(new TreeNode<>(1));
        underTest.setChildRight(new TreeNode<>(2));

        //Act
        boolean result = underTest.isLeave();

        //Assert
        Assertions.assertFalse(result);
    }
    @Test
    void isLeave_False_WhenLeftChildIsSet(){
        //Arrange
        var underTest = new TreeNode<>(0);
        underTest.setChildLeft(new TreeNode<>(1));

        //Act
        boolean result = underTest.isLeave();

        //Assert
        Assertions.assertFalse(result);
    }
    @Test
    void isLeave_False_WhenRightChildIsSet(){
        //Arrange
        var underTest = new TreeNode<>(0);
        underTest.setChildRight(new TreeNode<>(1));

        //Act
        boolean result = underTest.isLeave();

        //Assert
        Assertions.assertFalse(result);
    }
    @Test
    void isLeave_True_WhenBothChildrenNotSet(){
        //Arrange
        var underTest = new TreeNode<>(0);

        //Act
        boolean result = underTest.isLeave();

        //Assertion
        Assertions.assertTrue(result);
    }
    @Test
    void calculateLeaves_returnsThree_WhenTreeContainsThreeLeaves() {
        // Arrange
        var underTest = TreeFactory.convertToTree("0;1;2;3;4");

        // Act
        int result = underTest.calculateLeaves();

        // Assert
        Assertions.assertEquals(3, result);
    }

    @Test
    void calculateLeaves_returnsFive_WhenTreeContainsFiveLeaves() {
        // Arrange
        var underTest = TreeFactory.convertToTree("4;7;1;3;9;0;2;3;6;8");

        // Act
        int result = underTest.calculateLeaves();

        // Assert
        Assertions.assertEquals(5, result);
    }

    @Test
    void calculateLeaves_returnsNeverZero_WhenTreeContainsLeaves() {
        // Arrange
        var underTest = TreeFactory.convertToTree("4;7;1;3;9;0;2;3;6;8");

        // Act
        int result = underTest.calculateLeaves();

        // Assert
        Assertions.assertNotEquals(0, result);
    }

    @Test
    void calculateHeight_returnsOne_WhenTreeIsEmpty() {
        // Arrange
        var underTest = new TreeNode<Integer>(100);

        // Act
        int result = underTest.calculateHeight();

        // Assert
        Assertions.assertEquals(1, result);
    }

    @Test
    void calculateHeight_returnsFour_WhenTreeIsNotBalancedWithFourNodes() {
        // Arrange
        var underTest = new TreeNode<>(1);
        var node2 = new TreeNode<>(2);
        underTest.setChildRight(node2);
        var node3 = new TreeNode<>(3);
        node2.setChildRight(node3);
        var node4 = new TreeNode<>(4);
        node3.setChildRight(node4);

        // Act
        int result = underTest.calculateHeight();

        // Assert
        Assertions.assertEquals(4, result);
    }

    @Test
    void calculateHeight_returnsTwo_WhenTreeIsBalancedWithThreeNodes() {
        // Arrange
        var underTest = new TreeNode<>(1);
        var node2 = new TreeNode<>(2);
        underTest.setChildRight(node2);
        var node3 = new TreeNode<>(3);
        underTest.setChildLeft(node3);

        // Act
        int result = underTest.calculateHeight();

        // Assert
        Assertions.assertEquals(2, result);
    }

    @Test
    void calculateHeight_returnsFour_WhenTreeIsBalancedWithTenNodes() {
        // Arrange
        var underTest = TreeFactory.convertToTree("4;7;1;3;9;0;2;3;6;8");

        // Act
        int result = underTest.calculateHeight();

        // Assert
        Assertions.assertEquals(4, result);
    }
}