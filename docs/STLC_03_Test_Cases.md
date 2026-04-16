# STLC 03: Bộ Test Cases Manual Chi tiết (High Coverage) - Active eCommerce CMS

## 1. Module: Xác thực (Authentication)

| ID | Tiêu đề | Các bước thực hiện | Kết quả mong đợi | Ưu tiên |
|----|---------|--------------------|------------------|---------|
| AUTH_01 | Đăng nhập thành công (Positive) | 1. Truy cập trang Login.<br>2. Nhập Email & Password hợp lệ.<br>3. Click Login. | Hệ thống chuyển hướng vào trang Dashboard. Hiển thị đúng tên User. | High |
| AUTH_02 | Đăng nhập thất bại - Sai Password | 1. Nhập Email đúng.<br>2. Nhập Password sai.<br>3. Click Login. | Hiển thị thông báo lỗi "Invalid login credentials". Không cho vào Dashboard. | High |
| AUTH_03 | Đăng nhập thất bại - Sai định dạng Email | 1. Nhập Email không có dấu "@".<br>2. Nhập Password.<br>3. Click Login. | Hệ thống báo lỗi định dạng Email (Validation phía Client hoặc Server). | Medium |
| AUTH_04 | Chức năng "Remember Me" | 1. Tick chọn "Remember Me".<br>2. Login thành công.<br>3. Đóng trình duyệt & mở lại trang chủ. | Người dùng vẫn đang ở trạng thái đã đăng nhập. | Medium |

## 2. Module: Quản lý Profile (Profile Management)

| ID | Tiêu đề | Các bước thực hiện | Kết quả mong đợi | Ưu tiên |
|----|---------|--------------------|------------------|---------|
| PROF_01 | Cập nhật thông tin cơ bản | 1. Vào Manage Profile.<br>2. Thay đổi Name và Phone.<br>3. Click Update Profile. | Hiển thị thông báo "Your Profile has been updated successfully!". Dữ liệu mới hiển thị trên Header. | High |
| PROF_02 | Cập nhật mật khẩu mới | 1. Nhập New Password & Confirm Password giống nhau.<br>2. Click Update. | Thông báo thành công. Thử Logout và Login lại bằng Pass mới phải OK. | High |
| PROF_03 | Kiểm tra Validation Confirm Password | 1. Nhập New Password.<br>2. Nhập Confirm Password khác hoàn toàn.<br>3. Click Update. | Hệ thống báo lỗi mật khẩu xác nhận không trùng khớp. | Medium |
| PROF_04 | Thêm địa chỉ giao hàng mới | 1. Click "Add New Address".<br>2. Điền đầy đủ: Address, Country, City, Phone.<br>3. Click Save. | Địa chỉ mới hiển thị trong danh sách. Có thể chọn được ở bước Checkout. | High |

## 3. Module: Tìm kiếm & Sản phẩm (Search & Product)

| ID | Tiêu đề | Các bước thực hiện | Kết quả mong đợi | Ưu tiên |
|----|---------|--------------------|------------------|---------|
| SRCH_01 | Tìm kiếm chính xác tên SP | 1. Nhập "Laptop Dell XPS 15" vào ô Search. | Sản phẩm mục tiêu xuất hiện ngay trong danh sách gợi ý. | High |
| SRCH_02 | Xem chi tiết sản phẩm | 1. Click vào SP "Laptop Dell XPS 15" từ kết quả search. | Hiển thị đúng: Tên SP, Giá ($1,500.00), Đơn vị bán (Inhouse product). | High |
| SRCH_03 | Tìm kiếm sản phẩm không tồn tại | 1. Nhập chuỗi ký tự vô nghĩa (vd: "abcxyz123"). | Hệ thống hiển thị thông báo "No product found" hoặc danh sách trống. | Medium |

## 4. Module: Giỏ hàng & Thanh toán (Cart & Checkout)

| ID | Tiêu đề | Các bước thực hiện | Kết quả mong đợi | Ưu tiên |
|----|---------|--------------------|------------------|---------|
| ORD_01 | Thêm sản phẩm vào giỏ hàng | 1. Tại trang chi tiết SP, click "Add to cart". | Hiển thị Modal "Item added to your cart!". Số lượng icon Cart tăng lên. | High |
| ORD_02 | Kiểm tra trang giỏ hàng (My Cart) | 1. Click vào icon Cart -> View Cart.<br>2. Kiểm tra SP và Giá Subtotal. | Hiển thị đúng SP đã thêm. Giá Subtotal = Đơn giá x Số lượng. | High |
| ORD_03 | Luồng Checkout thành công (5 bước) | 1. Click "Continue to Shipping".<br>2. Chọn địa chỉ.<br>3. Chọn Home Delivery.<br>4. Chọn Cash on Delivery & Đồng ý điều khoản.<br>5. Click Complete Order. | Hiển thị trang Confirmation: "Thank You for Your Order!". Có mã Order Code. | High |
| ORD_04 | Kiểm tra Order Code trong lịch sử | 1. Copy mã Order Code vừa nhận.<br>2. Vào menu Purchase History. | Mã đơn hàng mới nhất phải trùng khớp với mã ở bước Confirmation. | High |
| ORD_05 | Áp dụng mã giảm giá (Coupon) | 1. Tại bước Payment, nhập mã Coupon hợp lệ.<br>2. Click Apply. | Tổng tiền thanh toán (Total) phải được trừ đi giá trị của Coupon. | Medium |
