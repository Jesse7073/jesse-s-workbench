# 資料庫命名與設計慣例

所有模組的資料表都遵循以下規則。

## 主鍵
- 型別：`bigint GENERATED ALWAYS AS IDENTITY`
- 欄位名：`id`
- 公開資源若需要好讀的網址，可另加 `slug`（`UNIQUE`），是否使用由各模組決定

## 命名
- 全小寫 + 底線（snake_case）
- 表名用單數
- 表名加模組前綴：`<模組>_<名稱>`，例如 `checkin_record`
- 跨模組共用的表用 `common_` 前綴（例如使用者表）
- 外鍵欄位：`<被引用實體>_id`，例如 `user_id`
- 避免保留字（`user`、`order`、`group`…）

## 稽核欄位
每張表都有：
- `created_at timestamptz NOT NULL DEFAULT now()`
- `updated_at timestamptz NOT NULL DEFAULT now()`
- 由 JPA 的 `@CreationTimestamp` / `@UpdateTimestamp` 更新

## 刪除
- 依資料重要性決定是否軟刪除，不強制統一
- 軟刪除欄位一律叫 `deleted_at timestamptz`（NULL 代表未刪除）
- 有 `UNIQUE` 的軟刪除表，改用部分唯一索引（`WHERE deleted_at IS NULL`）

## 時間
- 時間點用 `timestamptz`（Java：`Instant` / `OffsetDateTime`）
- 日期用 `date`（Java：`LocalDate`），以 `Asia/Taipei` 判定
- 不用 `timestamp`（不含時區）

## 外鍵與索引
- 同模組內使用 FK；之後會拆成獨立服務的模組之間不設 FK
- FK 欄位一律加索引
