/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicalrefactorings.floodfill;

import java.awt.Color;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collection;
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
				continue;
			}
			copy.set(toColor, at.x(), at.y());
			Collection<Position> neighbors = neighborsOf(at);
			Collection<Position> uncoloredNeighbors = new ArrayList<>();
			for (Position position : neighbors) {
				if (isIn(position, copy)) {
					Color colorAtPosition = copy.get(position.x(), position.y());
					if (colorAtPosition.equals(replacingColor)) {
						uncoloredNeighbors.add(position);
					}
				}
			}
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
