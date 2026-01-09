# TrafficLight

信号機の動作をシミュレートするAndroidアプリです。

## 概要

このアプリは、実際の信号機のように赤・黄・緑のライトが自動的に切り替わります。

各信号の残り時間もリアルタイムで確認できます。

<img src="./image/screenshot-for-readme.png" alt="Traffic Light Screenshot" width="135" />

## 主な機能

- **自動信号切替**: 緑 → 黄 → 赤 → 緑の順で自動的に信号が切り替わります
- **残り時間表示**: 各信号の残り秒数をリアルタイムで表示します
- **設定している表示時間**:
  - 緑: 37秒
  - 黄: 3秒
  - 赤: 20秒

## 技術スタック

### 開発環境
- **言語**: Kotlin
- **UI**: Jetpack Compose
- **最小SDK**: Android 7.0 (API 24)
- **ターゲットSDK**: Android 15 (API 36)

### アーキテクチャ
- **MVVMパターン**: ViewModel を使用した状態管理
- **クリーンアーキテクチャ**: Domain / Data / UI の3層構造
- **リアクティブプログラミング**: Kotlin Coroutines と StateFlow

### 主要ライブラリ
- Jetpack Compose - モダンなUI構築
- Lifecycle ViewModel - 状態管理とライフサイクル対応
- Kotlin Coroutines - 非同期処理

## プロジェクト構成

```
app/src/main/java/com/vitantonio/nagauzzi/trafficlight/
├── domain/              # ドメイン層
│   ├── LightColor.kt   # 信号の色を表す列挙型
│   └── TrafficLightConfig.kt  # 信号の設定値
├── data/                # データ層
│   ├── TrafficLightState.kt   # 信号の状態
│   └── repository/
│       ├── TrafficLightRepository.kt      # リポジトリインターフェース
│       └── TrafficLightRepositoryImpl.kt  # リポジトリ実装
└── ui/                  # UI層
    ├── screens/
    │   ├── TrafficLightScreen.kt     # メイン画面
    │   └── TrafficLightViewModel.kt  # ViewModel
    ├── components/
    │   ├── TrafficLight.kt           # 信号灯コンポーネント
    │   └── TrafficLightBox.kt        # 信号機ボックス
    ├── model/
    │   └── TrafficLightUiState.kt    # UI状態
    └── theme/                         # テーマ設定
```

## セットアップ

### 必要要件
- Android Studio Ladybug | 2024.2.1 以降
- JDK 11 以降
- Android SDK 36

### インストール手順

1. リポジトリをクローン:
```bash
git clone https://github.com/tonionagauzzi/TrafficLight.git
cd TrafficLight
```

2. Android Studioでプロジェクトを開く

3. 依存関係の同期:
   - Android Studioが自動的にGradle同期を開始します
   - またはメニューから `File > Sync Project with Gradle Files` を実行

## 実行方法

### エミュレータまたは実機で実行

1. Android Studioのツールバーで実行ボタン（▶️）をクリック
2. ターゲットデバイスを選択
3. アプリが起動し、信号機のアニメーションが自動的に開始されます

### コマンドラインから実行

```bash
./gradlew installDebug
```

## テスト

このプロジェクトには、ユニットテストとUIテストが含まれています。

### テストの実行

すべてのテストを実行:
```bash
./gradlew test
```

特定のテストクラスを実行:
```bash
./gradlew test --tests TrafficLightViewModelTest
./gradlew test --tests TrafficLightContentTest
```

### テストカバレッジ
- `TrafficLightViewModelTest` - ViewModelのビジネスロジックをテスト
- `TrafficLightContentTest` - UI表示のテスト

## アーキテクチャの特徴

### 関心の分離
- **Domain層**: ビジネスルールと設定（信号の表示時間、次の信号の決定）
- **Data層**: 状態管理とデータの永続化（リポジトリパターン）
- **UI層**: 画面表示とユーザーインタラクション（Compose）

### 単方向データフロー
```
Repository -> ViewModel -> UI
    ↓            ↓          ↓
  StateFlow   StateFlow   Composable
```

### テスタビリティ
- ViewModelとUI表示ロジックを分離
- 依存性注入が可能な設計
- Robolectricを使用したUIテスト

## ライセンス

このプロジェクトは学習目的で作成されました。

## 作者

Vitantonio Nagauzzi (@tonionagauzzi)
