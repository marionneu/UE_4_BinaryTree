package at.fhv.mne8893.binarytree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeFactoryTest {

    @Test
    void convertToTree_throwsArgumentException_whenDataLineIsNull() {
        //Arrange
        String dataLine = null;

        // Act
        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TreeFactory.convertToTree(dataLine);
        });
    }

    @Test
    void convertToTree_throwsArgumentException_whenDataLineIsEmpty() {
        //Arrange
        String dataLine = "";

        // Act
        // Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TreeFactory.convertToTree(dataLine);
        });
    }

    @Test
    void convertToTree_returnsNull_whenDataLineContainsNoData() {
        //Arrange
        String dataLine = " ";

        // Act
        TreeNode<Integer> result = TreeFactory.convertToTree(dataLine);

        // Assert
        Assertions.assertNull(result);
    }

    @Test
    void convertToTree_returnsTreeWithCorrectValue_whenDataLineContainsOneDataPoint() {
        //Arrange
        String dataLine = "1";

        // Act
        TreeNode<Integer> result = TreeFactory.convertToTree(dataLine);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(Integer.valueOf(1), result.getValue());
    }

    @Test
    void convertToTree_returnsTreeWithCorrectValue_whenDataLineContainsOneDataPointWithBlanks() {
        //Arrange
        String dataLine = " 1  ";

        // Act
        TreeNode<Integer> result = TreeFactory.convertToTree(dataLine);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(Integer.valueOf(1), result.getValue());
    }




    @Test
    void convertToTree_returnsNull_whenDataSetContainsNoElement() {
        //Arrange
        String dataSet = new String("0");

        // Act
        TreeNode<Integer> result = TreeFactory.convertToTree(dataSet);

        // Assert
        Assertions.assertNull(result);
    }
/*
    @Test
    void convertToTree_returnsRootWithTwoChildren_whenDataSetContainsThreeElement() {
        //Arrange
        Integer[] dataSet = new Integer[] {1,2,3};

        // Act
        TreeNode<Integer> result = TreeFactory.convertToTree(dataSet);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getChildLeft());
        Assertions.assertNotNull(result.getChildRight());
    }

    @Test
    void convertToTree_returnsRootWithSecondElementIsOnLeftChild_whenDataSetContainsTwoElement() {
        //Arrange
        Integer[] dataSet = new Integer[] {1,2};

        // Act
        TreeNode<Integer> result = TreeFactory.convertToTree(dataSet);

        // Assert
        Assertions.assertEquals(dataSet[1], result.getChildLeft().getValue());
    }

    @Test
    void convertToTree_returnsRootWithSecondElementIsOnLeftChild_whenDataSetContainsThreeElement() {
        //Arrange
        Integer[] dataSet = new Integer[] {1,2,3};

        // Act
        TreeNode<Integer> result = TreeFactory.convertToTree(dataSet);

        // Assert
        Assertions.assertEquals(dataSet[1], result.getChildLeft().getValue());
    }

    @Test
    void convertToTree_returnsRootWithThirdElementIsOnRightChild_whenDataSetContainsThreeElement() {
        //Arrange
        Integer[] dataSet = new Integer[] {1,2,3};

        // Act
        TreeNode<Integer> result = TreeFactory.convertToTree(dataSet);

        // Assert
        Assertions.assertEquals(dataSet[2], result.getChildRight().getValue());
    }

    @Test
    void convertToTree_returnTreeWithTwoLevels_whenDataSetContainsFourElement() {
        //Arrange
        Integer[] dataSet = new Integer[] {1,2,3,4};

        // Act
        TreeNode<Integer> result = TreeFactory.convertToTree(dataSet);

        // Assert
        Assertions.assertEquals(dataSet[3], result.getChildLeft().getChildLeft().getValue());
    }
*/
}