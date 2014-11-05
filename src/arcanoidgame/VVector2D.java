package arcanoidgame;


import java.awt.geom.Point2D;
import java.util.Vector;

public class VVector2D {

	private float [] v = new float[2];

	public VVector2D() {
		v[0] = v[1] = 0;
	}

	public VVector2D( float x, float y ) {
		v[0] = x;
		v[1] = y;
	}

	public VVector2D( Point2D P ) {
		v[0] = (float)P.getX();
		v[1] = (float)P.getY();
	}

	public void copy( float x, float y ) {
		v[0] = x;
		v[1] = y;
	}

	public void copy( VVector2D V ) {
		v[0] = V.v[0];
		v[1] = V.v[1];
	}

	public float x() { return v[0]; }
	public float y() { return v[1]; }

	public float lengthSquared() {
		return x()*x() + y()*y();
	}
	public float length() {
		return (float)Math.sqrt( lengthSquared() );
	}

	// The returned angle is in [0, 2*pi]
	public float angle() {
		return angle( 0 );
	}

	// The returned angle is in [lowerBound, lowerBound+2*pi]
	public float angle(
		float lowerBound // in radians
	) {
		float l = length();
		if ( l <= 0 )
			return lowerBound;
		float angle = (float)Math.asin(y()/l);
		// Now angle is in [-pi/2,pi/2]
		if ( x() < 0 )
			angle = (float)Math.PI - angle;
		// Now angle is in [-pi/2,3*pi/2]

		float TwoPi = (float)( 2*Math.PI );
		float upperBound = lowerBound + TwoPi;
		while ( angle > upperBound )
			angle -= TwoPi;
		while ( angle < lowerBound )
			angle += TwoPi;
		// Now angle is in [lowerBound, upperBound]
		return angle;
	}

	public VVector2D negated() {
		return new VVector2D(-x(),-y());
	}

	public VVector2D normalized() {
		float l = length();
		if ( l > 0 ) {
			float k = 1/l; // scale factor
			return new VVector2D(k*x(),k*y());
		}
		else return new VVector2D(x(),y());
	}

	// returns the dot-product of the given vectors
	static public float dot( VVector2D a, VVector2D b ) {
		return a.x()*b.x() + a.y()*b.y();
	}

	// returns the sum of the given vectors
	static public VVector2D sum( VVector2D a, VVector2D b ) {
		return new VVector2D( a.x()+b.x(), a.y()+b.y() );
	}

	// returns the difference of the given vectors
	static public VVector2D diff( VVector2D a, VVector2D b ) {
		return new VVector2D( a.x()-b.x(), a.y()-b.y() );
	}

	// returns the product of the given vector and scalar
	static public VVector2D mult( VVector2D a, float b ) {
		return new VVector2D( a.x()*b, a.y()*b );
	}

	// Computes the angle of rotation from v1 to v2 around the origin.
	// The angle returned is in the interval [-pi,pi],
	// where a positive angle corresponds to a counterclockwise rotation
	// (assuming x+ right, y+ up).
	static public float computeSignedAngle( VVector2D v1, VVector2D v2 ) {
		//return Vector3D.computeSignedAngle(
		//	new Vector3D( v1.x(), v1.y(), 0 ),
		//	new Vector3D( v2.x(), v2.y(), 0 ),
		//	new Vector3D( 0, 0, 1 )
		//);

		float productOfLengths = v1.length() * v2.length();
		if ( productOfLengths <= 0 )
			return 0;

		// Compute the z component of the cross product of v1 and v2
		// (Note that the x and y components of the cross product are zero,
		// because the z components of a and b are both zero)
		double crossProduct_z = v1.x()*v2.y() - v1.y()*v2.x();

		double sineOfAngle = Math.abs(crossProduct_z) / productOfLengths;

		// Due to numerical inaccuracies, the sine we computed
		// may be slightly more than 1.
		// Calling arcsin on such a value could be bad, so we don't.
		double angle = ( sineOfAngle >= 1 ) ? Math.PI/2 : Math.asin( sineOfAngle );

		// Compute the dot product of v1 and v2
		float dotProduct = VVector2D.dot( v1, v2 );

		if ( dotProduct < 0 )
			angle = Math.PI - angle;

		if ( crossProduct_z < 0 )
			angle = - angle;

		return (float)angle;
	}

        public static VVector2D reflect(VVector2D vector, VVector2D normal){
            float vDotX2 = VVector2D.dot(vector,normal)*2;
            return vector.diff(vector,vector.mult(normal,vDotX2)); 
        }
}
