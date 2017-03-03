package softuni.parsers;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import softuni.parsers.interfaces.ModelParser;

import javax.ejb.Local;
import javax.ejb.Stateless;


@Stateless
@Local(ModelParser.class)
public class ModelParserImpl implements ModelParser {

	private ModelMapper modelMapper;

	public ModelParserImpl() {
		this.modelMapper = new ModelMapper();
	}

	@Override
	public <S, D> D convert(S source, Class<D> destinationClass) {
		D convertedObject = null;
		convertedObject = this.modelMapper.map(source, destinationClass);
		return convertedObject;
	}

	@Override
	public <S, D> D convert(S source, Class<D> destinationClass,
			PropertyMap<S, D> propertyMap) {
		this.modelMapper = new ModelMapper();
		D convertedObject = null;
		modelMapper.addMappings(propertyMap);
		convertedObject = this.modelMapper.map(source, destinationClass);
		return convertedObject;
	}
}
