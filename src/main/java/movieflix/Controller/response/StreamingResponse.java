package movieflix.Controller.response;

import lombok.Builder;

@Builder
public record StreamingResponse(Long id, String name) {
}
