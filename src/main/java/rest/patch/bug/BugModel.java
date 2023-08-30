package rest.patch.bug;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
public class BugModel
{
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class NestedModel
	{
		private String value;
	}

	@Id
	private String id;

	private List<NestedModel> list;
}
