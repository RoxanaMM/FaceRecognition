package logicPackage.processing;

/**
 * Information about recognized user.
 */
public class RecognizedUser {
	private final int label;
	private final double confidence;

	public RecognizedUser(int label, double confidence) {
		this.label = label;
		this.confidence = confidence;
	}

	public int getLabel() {
		return label;
	}

	public double getConfidence() {
		return confidence;
	}

}
