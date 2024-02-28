package top.jiaojinxin.data.dict;

import com.mybatisflex.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.jiaojinxin.common.model.dict.Dict;
import top.jiaojinxin.data.constants.DataConstants;
import top.jiaojinxin.data.constants.DictDescriptionI18nCodeConstants;

/**
 * 字典：是否
 *
 * @author JiaoJinxin
 */
@Getter
@RequiredArgsConstructor
public enum YesNo implements Dict<Integer> {
    YES(DataConstants.DICT_YES_NO_YES, DictDescriptionI18nCodeConstants.YES_NO_YES),
    NO(DataConstants.DICT_YES_NO_NO, DictDescriptionI18nCodeConstants.YES_NO_NO);

    /**
     * 值
     */
    @EnumValue
    private final Integer value;

    /**
     * 描述对应的code值
     */
    private final String descriptionCode;
}
