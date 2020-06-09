package University;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class Student {
  @NonNull
  private Group group;
  @NonNull
  private String name;
  private int age;
}
