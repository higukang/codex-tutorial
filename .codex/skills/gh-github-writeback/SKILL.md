---
name: gh-github-writeback
description: GitHub MCP 쓰기 작업 승인 대신 로컬 gh CLI로 이슈 라벨 변경, Draft PR 생성, PR 코멘트 작성을 수행할 때 사용하는 스킬.
---

# gh-github-writeback

이 스킬은 GitHub에 대한 쓰기 작업을 GitHub MCP 도구 대신 로컬 `gh` CLI로 처리해야 할 때 사용한다.

## 이 스킬이 맡는 일
- 이슈 라벨 추가 또는 변경
- Draft PR 생성
- PR 코멘트 작성
- 자동화 흐름에서 필요한 소규모 GitHub 쓰기 작업

## 이 스킬이 맡지 않는 일
- 이슈 검색 또는 우선순위 판단
- 어떤 이슈를 구현할지 결정하는 일
- 코드 수정, 테스트 실행, 구현 판단
- 읽기 전용 GitHub 조회 작업

## 사용 전 확인
다음 항목을 먼저 확인한다.

- 대상 저장소가 무엇인지 확인한다
- 로컬에 `gh` 명령이 있는지 확인한다
- 필요하면 `gh auth status` 로 인증 상태를 확인한다
- 저장소 정보가 명확하지 않으면 현재 로컬 git remote 기준으로 판단한다

`gh` 가 없거나 인증되지 않았다면, 그 상태를 짧고 명확하게 보고하고 중단한다.

## 기본 원칙
- GitHub 읽기 작업은 가능하면 GitHub MCP 도구를 사용한다
- GitHub 쓰기 작업은 이 스킬에서 `gh` 로 처리한다
- 사용자가 명시적으로 요구하지 않으면 쓰기 작업을 다시 MCP로 되돌리지 않는다

## 표준 작업

### 1. 성공 시 이슈 라벨 처리
작업이 성공적으로 끝났다면:
- 해당 이슈에 `codex:ready` 라벨을 삭제한다
- 해당 이슈에 `codex:completed` 라벨을 추가한다
- 다른 라벨은 사용자가 요청하지 않으면 제거하지 않는다

예시 명령 형태:
- `gh issue edit <issue-number> --add-label codex:completed`

### 2. 실패 시 이슈 라벨 처리
작업을 완료할 수 없다면:
- 해당 이슈에 `codex:ready` 라벨을 삭제한다
- 해당 이슈에 `codex:failed` 라벨을 추가한다
- 최종 보고에 실패 이유를 짧게 남긴다
- 라벨 갱신을 조용히 생략하지 않는다

예시 명령 형태:
- `gh issue edit <issue-number> --add-label codex:failed`

### 3. Draft PR 생성
구현과 검증이 끝난 뒤:
- 브랜치가 push 되어 있는지 확인한다
- Draft PR을 생성한다
- 제목은 이슈와 연결되도록 짧고 명확하게 작성한다
- 본문에는 변경 요약, 테스트 결과, 남은 위험 요소를 포함한다

예시 명령 형태:
- `gh pr create --draft --title "<title>" --body "<body>"`

이미 PR이 있다면 중복 생성하지 말고 기존 PR을 재사용한다.

### 4. Copilot 리뷰 요청 코멘트 작성
Draft PR 생성이 성공했다면:
- PR에 정확히 `@copilot review` 라는 코멘트를 작성한다

예시 명령 형태:
- `gh pr comment <pr-number-or-url> --body "@copilot review"`

## 작업 순서
성공한 경우 기본 순서는 다음과 같다.

1. 브랜치를 push 한다
2. Draft PR을 생성하거나 기존 PR을 확인한다
3. 이슈에 `codex:completed` 라벨을 추가한다
4. PR에 `@copilot review` 코멘트를 작성한다

실패한 경우 기본 순서는 다음과 같다.

1. 이슈에 `codex:failed` 라벨을 추가한다
2. 실패 원인을 최종 보고에 남긴다
3. 미완성 PR은 만들지 않는다

## 최종 보고
이 스킬을 사용한 뒤에는 아래 내용을 간단히 반환한다.

- 어떤 이슈에 어떤 라벨을 추가했는지
- Draft PR을 만들었는지, 만들었다면 URL이 무엇인지
- `@copilot review` 코멘트를 작성했는지
- `gh` 실행 중 실패한 항목이 있는지
