package reversi.impl;

import java.util.Arrays;

import reversi.api.Color;

public class MatrixUtil {
    public static Color[][] flipVertically(Color[][] matrix) {
	int length = matrix.length;
	Color[][] newMatrix = new Color[length][length];
	for (int row = 0; row < length; row++) {
	    for (int column = 0; column < length; column++) {
		newMatrix[row][column] = matrix[row][(length - 1) - column];
	    }
	}
	return newMatrix;
    }

    public static Color[][] rotateRight(Color[][] matrix) {
	int length = matrix.length;
	Color[][] newMatrix = new Color[length][length];
	for (int row = 0; row < length; row++) {
	    for (int column = 0; column < length; column++) {
		newMatrix[row][column] = matrix[(length - 1) - column][row];
	    }
	}
	return newMatrix;
    }

    public static boolean equivalent(Color[][] matrix1, Color[][] matrix2) {
	if (Arrays.deepEquals(matrix1, matrix2)) {
	    return true;
	}
	Color[][] otherMatrix = matrix2;
	for (int i = 0; i < 3; i++) {
	    Color[][] fliped = flipVertically(otherMatrix);
	    if (Arrays.deepEquals(matrix1, fliped)) {
		return true;
	    }
	    Color[][] rotated = rotateRight(otherMatrix);
	    if (Arrays.deepEquals(matrix1, rotated)) {
		return true;
	    }
	    otherMatrix = rotated;
	}
	return false;
    }

}
