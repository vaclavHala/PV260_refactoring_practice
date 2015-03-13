/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.floodfill;

import java.awt.Color;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Floodfill {

	public Grid<Color> fillAt(Grid<Color> original, int startX, int startY, Color toColor) {
		Position start = new Position(startX, startY);
		if (!isIn(start, original)) {
			throw new IndexOutOfBoundsException("Got " + new Position(startX, startY) + " but grid is only " + original.width() + "x" + original.height());
		}
		Grid<Color> copy = copy(original);
		Color replacingColor = original.get(startX, startY);
		if (replacingColor.equals(toColor)) {
			return copy;
		}
		Queue<Position> left = new LinkedList<>();
		left.add(new Position(startX, startY));

		while (!left.isEmpty()) {
			Position at = left.poll();
			if (!isIn(at, copy)) {
				//this happens when we are looking at position neighborOf
				//invoked on border position produced
				continue;
			}
			copy.set(toColor, at.x(), at.y());
			List<Position> neighbors = neighborsOf(at);
			List<Position> uncoloredNeighbors = selectNeighborsToFillNext(neighbors, copy, replacingColor);

			left.addAll(uncoloredNeighbors);
		}
		return copy;
	}

	private List<Position> neighborsOf(Position position) {
		return asList(
				new Position(position.x() + 1, position.y()),
				new Position(position.x(), position.y() + 1),
				new Position(position.x() - 1, position.y()),
				new Position(position.x(), position.y() - 1)
		);
	}

	private List<Position> selectNeighborsToFillNext(List<Position> neighbors, Grid<Color> fillingGrid, Color replacingColor) {
		List<Position> selected = new ArrayList<>();
		for (Position position : neighbors) {
			if (isIn(position, fillingGrid)) {
				Color colorAtPosition = fillingGrid.get(position.x(), position.y());
				if (colorAtPosition.equals(replacingColor)) {
					selected.add(position);
				}
			}
		}
		return selected;
	}

	private <T> Grid<T> copy(Grid<T> original) {
		Grid<T> copy = new ArrayBackedGrid<>(original.width(), original.height());
		for (int x = 0; x < original.width(); x++) {
			for (int y = 0; y < original.height(); y++) {
				copy.set(original.get(x, y), x, y);
			}
		}
		return copy;
	}

	private boolean isIn(Position position, Grid<?> grid) {
		return position.x() >= 0 && position.x() < grid.width()
				&& position.y() >= 0 && position.y() < grid.height();
	}
}
