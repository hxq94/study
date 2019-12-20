package com.tung.study.service;

        import com.tung.study.model.User;
        import com.tung.study.vo.ResultVO;

/**
 * Created by 123456 on 2019/12/17.
 */
public interface UserServcie {

    ResultVO selByUserId(User user);

    ResultVO selByUserPar(User user);

}
