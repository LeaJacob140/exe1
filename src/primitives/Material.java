package primitives;

public class Material {
	public Double3 kD=Double3.ZERO;
	public Double3 kS=Double3.ZERO;
	public Double3 kT=Double3.ZERO;
	public Double3 kR=Double3.ZERO;
	public int nShininess=0;
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
	public Material setKd(double kD) {
		this.kD = new Double3(kD);
		return this;
	}
	public Material setKs(double kS) {
		this.kS = new Double3(kS);
		return this;
	}
	public Material setKt(double kT) {
		this.kT = new Double3(kT);
		return this;
	}
	public Material setKr(double kR) {
		this.kR = new Double3(kR);
		return this;
	}
	public Material setKt(Double3 kT) {
		this.kT = kT;
		return this;
	}
	public Material setKr(Double3 kR) {
		this.kR = kR;
		return this;
	}
	
}
