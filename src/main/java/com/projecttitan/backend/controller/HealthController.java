package com.projecttitan.backend.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class HealthController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String healthCheck() {
        long uptimeSeconds = ManagementFactory.getRuntimeMXBean().getUptime() / 1000;
        long hours = uptimeSeconds / 3600;
        long minutes = (uptimeSeconds % 3600) / 60;
        long seconds = uptimeSeconds % 60;
        String uptime = String.format("%dh %dm %ds", hours, minutes, seconds);

        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String htmlTemplate = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Titan Realm | API Status</title>
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono:wght@400;600;700&display=swap" rel="stylesheet">
                <style>
                    * { box-sizing: border-box; margin: 0; padding: 0; }
                    body {
                        background-color: #0b0f19;
                        color: #f3f4f6;
                        font-family: 'JetBrains Mono', monospace;
                        min-height: 100vh;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        padding: 1.5rem;
                    }
                    .status-card {
                        background: rgba(17, 24, 39, 0.85);
                        border: 1px solid rgba(255, 255, 255, 0.1);
                        backdrop-filter: blur(12px);
                        border-radius: 16px;
                        padding: 2.5rem;
                        max-width: 480px;
                        width: 100%;
                        box-shadow: 0 20px 50px rgba(0, 0, 0, 0.5);
                    }
                    .header {
                        display: flex;
                        align-items: center;
                        justify-content: space-between;
                        margin-bottom: 2rem;
                        padding-bottom: 1.25rem;
                        border-bottom: 1px solid rgba(255, 255, 255, 0.08);
                    }
                    .title { font-size: 1.2rem; font-weight: 700; color: #ffffff; letter-spacing: -0.5px; }
                    .badge {
                        display: inline-flex;
                        align-items: center;
                        gap: 0.5rem;
                        background: rgba(16, 185, 129, 0.12);
                        color: #10b981;
                        border: 1px solid rgba(16, 185, 129, 0.3);
                        padding: 0.35rem 0.8rem;
                        border-radius: 9999px;
                        font-size: 0.8rem;
                        font-weight: 600;
                    }
                    .pulse-dot {
                        width: 8px;
                        height: 8px;
                        background: #10b981;
                        border-radius: 50%;
                        box-shadow: 0 0 8px #10b981;
                        animation: pulse 2s infinite ease-in-out;
                    }
                    @keyframes pulse {
                        0%, 100% { opacity: 1; transform: scale(1); }
                        50% { opacity: 0.4; transform: scale(0.85); }
                    }
                    .metrics {
                        display: flex;
                        flex-direction: column;
                        gap: 1rem;
                    }
                    .metric-row {
                        display: flex;
                        justify-content: space-between;
                        background: rgba(255, 255, 255, 0.03);
                        padding: 0.85rem 1rem;
                        border-radius: 8px;
                        border: 1px solid rgba(255, 255, 255, 0.04);
                    }
                    .label { color: #9ca3af; font-size: 0.85rem; }
                    .val { color: #60a5fa; font-size: 0.85rem; font-weight: 600; }
                    .footer {
                        margin-top: 2rem;
                        text-align: center;
                        font-size: 0.75rem;
                        color: #4b5563;
                    }
                </style>
            </head>
            <body>
                <div class="status-card">
                    <div class="header">
                        <div class="title">Project Titan Engine</div>
                        <div class="badge">
                            <span class="pulse-dot"></span> ONLINE
                        </div>
                    </div>
                    <div class="metrics">
                        <div class="metric-row">
                            <span class="label">System Status</span>
                            <span class="val" style="color: #10b981;">Healthy (HTTP 200)</span>
                        </div>
                        <div class="metric-row">
                            <span class="label">Database</span>
                            <span class="val">Supabase PostgreSQL</span>
                        </div>
                        <div class="metric-row">
                            <span class="label">Process Uptime</span>
                            <span class="val">{{UPTIME}}</span>
                        </div>
                        <div class="metric-row">
                            <span class="label">Server Timestamp</span>
                            <span class="val">{{CURRENT_TIME}}</span>
                        </div>
                    </div>
                    <div class="footer">
                        Powered by Spring Boot 4 &bull; Deployed on Render
                    </div>
                </div>
            </body>
            </html>
            """;

        return htmlTemplate
                .replace("{{UPTIME}}", uptime)
                .replace("{{CURRENT_TIME}}", currentTime);
    }
}