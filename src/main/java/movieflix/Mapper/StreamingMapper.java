package movieflix.Mapper;

import lombok.experimental.UtilityClass;
import movieflix.Controller.request.StreamingRequest;
import movieflix.Controller.response.StreamingResponse;
import movieflix.Entity.Streaming;

@UtilityClass
public class StreamingMapper{

    public static Streaming toStreaming(StreamingRequest streamingRequest){
        return Streaming
                .builder()
                .name(streamingRequest.name())
                .build();
    }

    public StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
