package datastructure.TD;

/**
 * two dimension Region
 */
public class _2DRegion {
	public _2DPoint min;
	public _2DPoint max;

	public _2DRegion() {

	}

	public _2DRegion(_2DPoint min, _2DPoint max) {
		this.min = min;
		this.max = max;
	}

	public double size() {
		return Math.abs(max.y - min.y) * Math.abs(max.x - min.x);
	}
}
