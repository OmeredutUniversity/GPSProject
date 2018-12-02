package GIS;
import java.util.Set;
public interface GIS_layer extends Set<GIS_element>{

	/**
	 *
	 * @return all The metaData of csv file.
	 */
	public Meta_data get_Meta_data(); 
	
}
