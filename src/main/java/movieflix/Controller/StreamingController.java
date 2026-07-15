package movieflix.Controller;

import lombok.RequiredArgsConstructor;
import movieflix.Controller.request.StreamingRequest;
import movieflix.Controller.response.StreamingResponse;
import movieflix.Entity.Streaming;
import movieflix.Mapper.StreamingMapper;
import movieflix.Service.StreamingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAllStreaming(){
        List<StreamingResponse> listStreaming = streamingService.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return ResponseEntity.ok(listStreaming);
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> save(@RequestBody StreamingRequest streaming){
        Streaming newStreaming = StreamingMapper.toStreaming(streaming);
        Streaming savedStreaming = streamingService.save(newStreaming);

        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(savedStreaming));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> findById(@PathVariable Long id){
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        streamingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
