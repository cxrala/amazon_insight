package weather_api;//For online compiler, use: https://www.programiz.com/java-programming/online-compiler/

import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLParameters;

class WeatherFetcher implements Runnable {

    private static final Pattern p = Pattern.compile("\"the_temp\":([0-9]+)\\.([0-9]+)");
    Cache cache = new Cache(5);

    private static HashMap<String, String> cities = new HashMap<>() {{
        put("london", "44418");
        put("sanfransico","2487956");
        put("dublin", "560743");
        put("dubai", "1940345");
        put("toronto", "4118");
        put("santorini", "56558361");
        put("paris", "615702");
        put("berlin", "638242");
        put("belfast", "44544");
        put("sydney", "1105779");
        put("madrid", "766273");
        put("frankfurt", "650272");
        put("tokyo", "1118370");
        put("beijing", "2151330");
        put("cairo", "1521894");
    }};

    private String temperature = null;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a city > ");
        String city = scanner.nextLine().toLowerCase(Locale.ROOT);
        System.out.println("Enter how many days in the future > ");
        String days = scanner.nextLine();
        while (!days.matches("-?\\d+")) {
            System.out.println("Please give a valid date format (0-5) > ");
            days = scanner.nextLine();
        }

        long startTime = System.currentTimeMillis();
        System.out.println(getTemperature(city, Integer.parseInt(days)));
        System.out.println("Getting temperature took " + (System.currentTimeMillis() - startTime) + " milliseconds");
    }

    public String getTemperature(String city, int days) {
        try {
            if (!cities.containsKey(city)) {
                return String.format("The city %s doesn't exist!", city);
            }
            if (cache.cacheContainsKey(city)) {
                return cache.get(city);
            }
            var client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(60))
                    .executor(Executors.newFixedThreadPool(3))
                    .followRedirects(Redirect.NEVER)
                    .priority(2)
                    .proxy(ProxySelector.getDefault())
                    .version(Version.HTTP_2)
                    .sslParameters(new SSLParameters())
                    .build();
            var httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("https://www.metaweather.com/api/location/%s/", cities.get(city))))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(httpRequest, BodyHandlers.ofString());
            cache.put(city, parseOutTemperature(response.body(), days));
            return  parseOutTemperature(response.body(), days);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private String parseOutTemperature(String json, int days) {
        Matcher m = p.matcher(json);
        if (days < 5 && days >= 0) {
            for (int i = 0; i < days - 1; i++) {
                m.find();
            }
            if (m.find()) {
                return String.format("%.2f", Double.parseDouble(m.group(1) + "." + m.group(2)));
            }
        }
        return "unknown";
    }
}