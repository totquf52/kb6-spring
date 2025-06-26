package org.scoula.common.util;

public class UploadFileName {
    public static String getUniqueName(String filename) {

        int ix = filename.lastIndexOf(".");  // 파일명에서 확장자 위치 추출
        String name = filename.substring(0, ix);  // 파일명 추출
        String ext = filename.substring(ix + 1);  // 확장명 추출
        // 파일명에 타임스탬프 추가하여 유니크한 이름 생성
        return String.format("%s-%d.%s", name, System.currentTimeMillis(), ext);
    }
}