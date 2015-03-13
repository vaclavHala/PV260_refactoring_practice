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
import java.util.Queue;

public class Floodfill {

	public Grid<Color> fillAt(Grid<Color> original, int startX, int startY, Color color) {
		Position start = new Position(startX, startY);
		if (!isIn(start, original)) {
			throw new IndexOutOfBoundsException("Got " + new Position(startX, startY) + " but grid is only " + original.width() + "x" + original.height());
		}
		Grid<Color> copy = new ArrayBackedGrid<>(original.width(), original.height());
		for (int x = 0; x < original.width(); x++) {
			for (int y = 0; y < original.height(); y++) {
				copy.set(original.get(x, y), x, y);
			}
		}
		Queue<Position> left = new LinkedList<>();
		left.add(new Position(startX, startY));
		Color replacingColor = original.get(startX, startY);
		if (!replacingColor.equals(color)) {
			while (!left.isEmpty()) {
				Position at = left.poll();
				if (isIn(at, copy)) {
					copy.set(color, at.x(), at.y());
					Collection<Position> neighbors = asList(
							new Position(at.x() + 1, at.y()),
							new Position(at.x(), at.y() + 1),
							new Position(at.x() - 1, at.y()),
							new Position(at.x(), at.y() - 1)
					);
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
			}
		}
		return copy;
	}

	private boolean isIn(Position position, Grid<?> grid) {
		return position.x() >= 0 && position.x() < grid.width()
				&& position.y() >= 0 && position.y() < grid.height();
	}
}
