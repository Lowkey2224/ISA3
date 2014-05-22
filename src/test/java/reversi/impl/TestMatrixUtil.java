package reversi.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import reversi.api.Color;

public class TestMatrixUtil {
    @Test
    public void testFlip1(){
	Color[][] matrix = {
		{Color.BLACK, Color.WHITE, Color.EMPTY},
		{Color.WHITE, Color.BLACK, Color.BLACK},
		{Color.WHITE, Color.WHITE, Color.EMPTY}
		};

	Color[][] matrix1 = {
		{Color.EMPTY,Color.WHITE, Color.BLACK},
		{Color.BLACK, Color.BLACK, Color.WHITE},
		{ Color.EMPTY, Color.WHITE, Color.WHITE }
	};
	Color[][] result = MatrixUtil.flipVertically(matrix);
	Assert.assertArrayEquals(matrix1, result);
	Assert.assertArrayEquals(matrix, MatrixUtil.flipVertically(MatrixUtil.flipVertically(matrix)));
    }
    @Test
    public void testrotateRight() {
	Color[][] matrix = {
		{Color.BLACK, Color.WHITE, Color.EMPTY},
		{Color.WHITE, Color.BLACK, Color.BLACK},
		{Color.WHITE, Color.WHITE, Color.EMPTY}
		};

	Color[][] matrix1 = {
		{Color.WHITE, Color.WHITE, Color.BLACK},
		{Color.WHITE, Color.BLACK, Color.WHITE},
		{Color.EMPTY, Color.BLACK, Color.EMPTY }
	};
	Color[][] result = (MatrixUtil.rotateRight(matrix));
	// for (int i = 0; i < result.length; i++) {
	// System.out.print("\n");
	// for (int j = 0; j < result.length; j++) {
	// System.out.print(String.format("%s ,", result[i][j]));
	// }
	// }
	Assert.assertArrayEquals(matrix1, result);
	// Assert.assertArrayEquals(matrix,
	// MatrixUtil.flipVertically(MatrixUtil.flipVertically(matrix)));
    }

    @Test
    public void testEquivalent() {
	List<Color[][]> permutations = permutations(2);
	List<Color[][]> list = new ArrayList<>();
	for (Color[][] item : permutations) {
	    if (!contains(list, item)) {
		list.add(item);
	    }
	}

	// Assert.assertEquals(81, permutations.size());
	System.out.println(permutations.size());
	System.out.println(list.size());
	for (Color[][] item : list) {
	    print(item);
	}
    }

    private boolean contains(List<Color[][]> list, Color[][] color) {
	for (Color[][] item : list) {
	    if (MatrixUtil.equivalent(color, item)) {
		return true;
	    }
	}
	return false;
    }
    private List<Color[][]> permutations(int size) {
	Color[] colors = Color.values();
	List<Color[][]> list = new ArrayList<>((int) Math.pow(colors.length, size * size));
	for (int i = 0; i < Math.pow(colors.length, size * size); i++) {
	    int k = i;
	    Color[][] matrix = new Color[size][size];
	    for (int j = matrix.length * matrix.length - 1; j >= 0; j--) {
		int rest = k % colors.length;
		k = k / colors.length;
		int row = j % matrix.length;
		int column = j / matrix.length;
		matrix[row][column] = colors[rest];
	    }
	    list.add(matrix);
	}
	return list;
    }
    private void print(Color[][] color) {
	System.out.print("\n{");
	    for (int i = 0; i < color.length; i++) {
		System.out.print("\n");
		for (int j = 0; j < color.length; j++) {
		    System.out.print(String.format("%s ,", color[i][j]));
		}
	}
	System.out.println("}");
    }
}
