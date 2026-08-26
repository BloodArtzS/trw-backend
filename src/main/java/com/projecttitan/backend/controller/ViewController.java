package com.projecttitan.backend.controller;

import com.projecttitan.backend.model.ViewResponse;
import com.projecttitan.backend.service.ViewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/views")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://bloodartz.netlify.app"
})
public class ViewController {

    private final ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @PostMapping("/{chapterId}")
    public ViewResponse registerView(@PathVariable int chapterId, HttpServletRequest request) {
        String visitorIp = getClientIp(request);
        long views = viewService.registerView(chapterId, visitorIp);
        return new ViewResponse(chapterId, views);
    }

    @GetMapping("/{chapterId}")
    public ViewResponse getView(@PathVariable int chapterId) {
        long views = viewService.getViews(chapterId);
        return new ViewResponse(chapterId, views);
    }

    private String getClientIp(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isBlank()) {
            return forwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}