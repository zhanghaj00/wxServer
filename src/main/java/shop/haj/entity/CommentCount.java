/**
 * Create time
 */
package shop.haj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Hao on .
 * description。
 * @since 1.9.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCount implements Serializable{

    private Integer GOOD;

    private Integer NORMAL;

    private Integer BAD;

    private Integer ALL;
}
