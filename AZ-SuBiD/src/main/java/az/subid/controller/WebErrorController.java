//package az.subid.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * WebError는 에러 페이지를 담당하는 페이지입니다.
// * 현재는 컨트롤러만 존재합니다.
// */
//@Slf4j
//@Controller
//public class WebErrorController implements ErrorController {
//
////    @Override
////    public String getErrorPath() {
////        return null;
////    }
//
//    // 에러 페이지를 인위적으로 불러올 수 있다.
//    @RequestMapping("/errorpage/{errorcode}")
//    public String ErrorPage(@PathVariable int errorcode /*401*/, ModelMap model) throws Exception {
//
//        log.info(this.getClass().getName() + ".ErrorPage Start!");
//
//        model.addAttribute("errorcode", errorcode);
//
//        log.info(this.getClass().getName() + ".ErrorPage End!");
//
//        return "error/" + errorcode + "error";
//    }
//
//    // 에러 404로 이어줌
//    @GetMapping("/error")
//    public String handleError(HttpServletRequest request) {
//
//        log.info(this.getClass().getName() + ".handleError Start!");
//
//        // 에러 상태값을 집어넣음
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//        if(status != null) {
//
//            int statusCode = Integer.valueOf(status.toString());
//
//            // 오류 코드 보여주기
//            log.info(this.getClass().getName() + Integer.toString(statusCode));
//
//            log.info(this.getClass().getName() + ".handleError End!");
//
//            if(statusCode == HttpStatus.NOT_FOUND.value()) {
//                return "error/404error";
//            } else {
//                return "error/error";
//            }
//
//        }
//
//        log.info(this.getClass().getName() + ".handleError End!");
//
//        return "error/error";
//
//    }
//
//}
