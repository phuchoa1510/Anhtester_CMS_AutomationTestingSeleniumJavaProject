# STLC 02: Kế hoạch Kiểm thử (Test Plan) - Active eCommerce CMS

## 1. Mục tiêu (Objectives)
*   Xác minh các chức năng quan trọng (Login, Profile, Order) hoạt động chính xác theo yêu cầu.
*   Đảm bảo trải nghiệm người dùng (UX) mượt mà và không có lỗi hiển thị nghiêm trọng.
*   Kiểm tra tính nhất quán của dữ liệu từ lúc thêm sản phẩm đến khi thanh toán xong.

## 2. Phạm vi Kiểm thử (Scope)
*   **Module bao phủ:** Authentication, Profile, Search, Product Details, Cart, Checkout flow.
*   **Trình duyệt:** Chrome, Microsoft Edge (phiên bản mới nhất).
*   **Môi trường:** Production/Staging URL: `https://cms.anhtester.com`.

## 3. Chiến lược Kiểm thử (Testing Strategy)
*   **Kiểm thử Chức năng (Functional Testing):** Sử dụng các kịch bản Positive và Negative.
*   **Kiểm thử Giao diện (UI Testing):** Kiểm tra hiển thị Logo, Banner, các trường nhập liệu và Toast message.
*   **Kiểm thử Hồi quy (Regression Testing):** Đảm bảo các thay đổi ở Profile không ảnh hưởng đến luồng thanh toán.
*   **Dữ liệu Kiểm thử (Test Data):** Sử dụng dữ liệu từ file Excel `DataCMS.xlsx` và các dữ liệu random traceable.

## 4. Tiêu chí Nghiệm thu (Entrance & Exit Criteria)
*   **Entrance:** Hệ thống sẵn sàng truy cập, tài liệu phân tích đã hoàn thiện.
*   **Exit:** 100% Test Case mức độ "High" phải đạt (Pass). Không còn bug mức độ "Critical" hoặc "Major" chưa được giải quyết.

## 5. Tài liệu & Công cụ
*   **Công cụ quản lý:** GitHub (để lưu trữ script/doc).
*   **Báo cáo:** Manual Report (.md) và Allure Report (cho Automation sau này).
