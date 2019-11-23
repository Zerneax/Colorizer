package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataImage {

    private int X;
    private int Y;
    private int red;
    private int green;
    private int blue;
    private int alpha;
}
