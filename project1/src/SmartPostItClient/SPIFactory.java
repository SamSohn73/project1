package SmartPostItClient;

/**
 * Smart Post It 객체 생성 공장
 * 
 * @author		sam
 * @version		0.1
 */
class SPIFactory
{
	
	public SPIDocument createSPIDoc(SPIType type)
	{
		SPIDocument spi = null;
		spi = new SPIDocument(type);
		
		/*		if (type == SPIType.MEMO) {
			spi = new SPIDocument(SPIType.MEMO);
		}
		else if (type == SPIType.TODO) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.FAVORITE) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.GRAPHIC) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.CALCULATOR) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.VOICE_RECOGNITION) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.CHAR_RECOGNITION) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.CAMERA) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.CALENDAR) {
			spi = new SPIDocument(SPIType.TODO);
		}
		else if (type == SPIType.STOPWATCH) {
			spi = new SPIDocument(SPIType.TODO);
		}*/
		
		return spi; 
	}
}
