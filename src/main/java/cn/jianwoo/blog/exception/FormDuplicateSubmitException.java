package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;

/**
 * @author gulihua
 * @Description
 * @date 2021-01-07 11:56 下午
 */
public class FormDuplicateSubmitException extends JwBlogException {

    public static final FormDuplicateSubmitException FORM_DUPLICATE_SUBMIT_EXCEPTION =
            new FormDuplicateSubmitException(ExceptionConstants.FORM_DUPLICATE,
                    ExceptionConstants.FORM_DUPLICATE_DESC);

    public FormDuplicateSubmitException() {

    }

    public FormDuplicateSubmitException(String code, String msg) {
        super(code, msg);
    }
}
