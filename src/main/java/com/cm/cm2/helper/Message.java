package com.cm.cm2.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
/**
 * message
 */
public class Message {

    private String contant;
    @Builder.Default
    private MessageType type = MessageType.blue;

}
