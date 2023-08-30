package rest.patch.bug;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ObjectUtils;
import rest.patch.bug.BugModel.NestedModel;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BugApplicationTests
{
	@LocalServerPort
	int port;

	private final TestRestTemplate restTemplate;

	public BugApplicationTests()
	{
		RestTemplateBuilder builder = new RestTemplateBuilder().requestFactory(HttpComponentsClientHttpRequestFactory.class);
		this.restTemplate = new TestRestTemplate(builder);
	}

	private String create() throws URISyntaxException
	{
		URI uri = new URI(this.getBaseUrl());
		ResponseEntity<BugModel> result = this.restTemplate.postForEntity(uri, new BugModel(), BugModel.class);
		Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
		String id = result.getBody().getId();
		Assertions.assertTrue(ObjectId.isValid(id));
		return id;
	}

	private String getBaseUrl()
	{
		return "http://localhost:" + this.port + "/models";
	}

	@Test
	void patch1() throws URISyntaxException
	{
		String id = this.create();
		System.out.println(id);
		List<NestedModel> list = List.of(new NestedModel("patch1"));
		URI uri = new URI(this.getBaseUrl() + "/" + id);
		BugModel result = this.restTemplate.patchForObject(uri, Map.of("list", list), BugModel.class);
		Assertions.assertEquals(list, result.getList());
	}

	@Test
	void patch2() throws URISyntaxException
	{
		String id = this.create();
		System.out.println(id);
		List<NestedModel> list = List.of(new NestedModel("patch1"), new NestedModel("patch2"));
		URI uri = new URI(this.getBaseUrl() + "/" + id);
		BugModel result = this.restTemplate.patchForObject(uri, Map.of("list", list), BugModel.class);
		Assertions.assertEquals(list, result.getList());
	}

	@Test
	void patch3() throws URISyntaxException
	{
		String id = this.create();
		System.out.println(id);
		URI uri = new URI(this.getBaseUrl() + "/" + id);
		List<NestedModel> list = List.of(new NestedModel("patch1"), new NestedModel("patch2"));
		BugModel result = this.restTemplate.patchForObject(uri, Map.of("list", list), BugModel.class);
		Assertions.assertEquals(list, result.getList());
		list = List.of(new NestedModel("patch3"));
		result = this.restTemplate.patchForObject(uri, Map.of("list", list), BugModel.class);
		Assertions.assertEquals(list, result.getList());
	}

	@Test
	void patch4() throws URISyntaxException
	{
		String id = this.create();
		System.out.println(id);
		URI uri = new URI(this.getBaseUrl() + "/" + id);
		List<NestedModel> list = List.of(new NestedModel("patch1"), new NestedModel("patch2"));
		BugModel result = this.restTemplate.patchForObject(uri, Map.of("list", list), BugModel.class);
		Assertions.assertEquals(list, result.getList());
		list = List.of();
		result = this.restTemplate.patchForObject(uri, Map.of("list", list), BugModel.class);
		Assertions.assertTrue(ObjectUtils.isEmpty(result.getList()));
	}

	@Test
	void patch5() throws URISyntaxException
	{
		String id = this.create();
		System.out.println(id);
		URI uri = new URI(this.getBaseUrl() + "/" + id);
		List<NestedModel> list = List.of(new NestedModel("patch1"));
		BugModel result = this.restTemplate.patchForObject(uri, Map.of("list", list), BugModel.class);
		Assertions.assertEquals(list, result.getList());
		list = List.of(new NestedModel("patch1"), new NestedModel("patch2"));
		result = this.restTemplate.patchForObject(uri, Map.of("list", list), BugModel.class);
		Assertions.assertEquals(list, result.getList());
	}
}
