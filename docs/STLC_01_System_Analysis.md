# STLC 01: Tài liệu Phân tích Hệ thống (System Analysis) - Active eCommerce CMS

## 1. Giới thiệu
Tài liệu này phân tích các luồng nghiệp vụ và quy tắc dữ liệu của hệ thống Active eCommerce CMS (phiên bản User) để làm cơ sở cho việc thiết kế Test Case.

## 2. Các Module Trọng tâm & Phân tích Chi tiết

### 2.1 Module Xác thực (Authentication)
*   **Chức năng:** Login, Logout, Remember Me, Forgot Password.
*   **Quy tắc nghiệp vụ:**
    *   Email: Bắt buộc đúng định dạng (ví dụ: user@example.com).
    *   Password: Phân biệt chữ hoa, chữ thường.
    *   Bảo mật: Chặn truy cập trực tiếp vào các trang `/dashboard`, `/profile` nếu chưa login thành công.

### 2.2 Module Quản lý Tài khoản (Profile Management)
*   **Chức năng:** Update Basic Info (Name, Phone, Photo), Change Password, Manage Address, Change Email.
*   **Quy tắc nghiệp vụ:**
    *   Khi update tên/số điện thoại: Hệ thống phải hiển thị Toast thông báo thành công và cập nhật ngay lập tức trên Header/Sidebar.
    *   Thay đổi mật khẩu: Phải có bước xác nhận mật khẩu mới (Confirm Password) khớp với mật khẩu mới.
    *   Địa chỉ: Hỗ trợ thêm nhiều địa chỉ với các trường Country, State, City, Postal Code.

### 2.3 Module Tìm kiếm & Sản phẩm (Search & Product)
*   **Chức năng:** Global Search (Live search), Product Details.
*   **Quy tắc nghiệp vụ:**
    *   Live Search: Khi gõ từ khóa, danh sách gợi ý phải xuất hiện dưới dạng dropdown ngay dưới thanh search.
    *   Product Details: Hiển thị đúng: Tên SP, Giá hiện tại (sau khi trừ chiết khấu), Đơn vị bán (Sold by), Mô tả SP (Description).

### 2.4 Module Đặt hàng (Cart & Checkout)
*   **Chức năng:** Add to cart, View Cart, Checkout flow (5 bước).
*   **Quy trình 5 bước Checkout:**
    1.  **My Cart:** Kiểm tra số lượng sản phẩm và Subtotal.
    2.  **Shipping Info:** Chọn địa chỉ giao hàng đã lưu hoặc thêm mới.
    3.  **Delivery Info:** Chọn phương thức vận chuyển (Home Delivery).
    4.  **Payment:** Chọn phương thức thanh toán (ưu tiên Cash on Delivery để test manual), nhập mã Coupon.
    5.  **Confirmation:** Hệ thống tạo mã đơn hàng (Order Code) và lưu vào lịch sử mua hàng.

## 3. Luồng nghiệp vụ chính (Critical Flows)
*   **Flow 1:** Đăng nhập -> Tìm kiếm SP -> Xem chi tiết -> Thêm vào giỏ -> Thanh toán (COD) -> Kiểm tra Order Code trong Purchase History.
*   **Flow 2:** Đăng nhập -> Cập nhật Profile -> Kiểm tra tính đồng bộ của thông tin mới trên Dashboard.

## 4. Các ràng buộc kỹ thuật
*   Hệ thống sử dụng các thành phần động (Modal, Toast) nên cần đảm bảo trang đã load xong (readyState = complete) trước khi tương tác.
*   Dữ liệu nhập liệu cần được kiểm tra với các trường hợp: Để trống, nhập sai định dạng, nhập ký tự đặc biệt.
