package University;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class Group {
  @NonNull
  private String name;
  private int course;
}
