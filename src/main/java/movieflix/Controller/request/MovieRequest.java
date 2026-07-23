package movieflix.Controller.request;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(String name,
                           String description,
                           LocalDate releaseDate,
                           double rating,
                           List<Long> categories,
                           List<Long> streamings) {
}
