package pucrs.ages.garbus.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class FirebaseMessage {
    private final @NonNull String subject;
    private final @NonNull String content;
}
