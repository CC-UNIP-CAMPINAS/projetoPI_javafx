package br.com.leonardopn.model.entities;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;

public class EscalaDeCinza {
	BufferedImageOp op;

	public EscalaDeCinza() {
		this.op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
	}

	public BufferedImage getImgGray(BufferedImage img) {
		BufferedImage imgFinal = op.filter(img, null);
		return imgFinal;
	}
}