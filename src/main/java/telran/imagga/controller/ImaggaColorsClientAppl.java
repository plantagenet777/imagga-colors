package telran.imagga.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import telran.imagga.dto.ResponseDto;

public class ImaggaColorsClientAppl {
	static RestTemplate restTemplate = new RestTemplate();
	private static final String TOKEN = "Basic <token>";

	public static void main(String[] args) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", TOKEN);
		String url = "https://api.imagga.com/v2/colors";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("image_url", "https://images8.alphacoders.com/414/414960.jpg");
		RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET,builder.build().toUri());
		ResponseEntity<ResponseDto> response = restTemplate.exchange(requestEntity, ResponseDto.class);
		displayColors(response.getBody());

	}

	private static void displayColors(ResponseDto results) {
		if (results.getResult() == null) {
			System.out.println("Wrong url image");
		} else {
			System.out.println("color\t\tparent color\t\tpercent");
			System.out.println("----------------------------------------------");
			results.getResult().getColors().getListOfCollors()
				.forEach(System.out::println);
		}

	}

}
