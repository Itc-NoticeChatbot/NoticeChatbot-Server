package com.inhatc.noticebot.notice.crawler;

public class NoticeUrlNormalizer {

  public static String normalize(String url) {
    if (url == null) return null;

    String result = url.strip();
    result = result.replaceFirst("^http://", "https://");

    int fragmentIndex = result.indexOf('#');
    if (fragmentIndex != -1) {
      result = result.substring(0, fragmentIndex);
    }

    if (result.contains("?")) {
      String base = result.substring(0, result.indexOf('?'));
      String query = result.substring(result.indexOf('?') + 1);
      String filteredQuery =
          java.util.Arrays.stream(query.split("&"))
              .filter(param -> !param.startsWith("utm_"))
              .collect(java.util.stream.Collectors.joining("&"));
      result = filteredQuery.isEmpty() ? base : base + "?" + filteredQuery;
    }

    return result;
  }
}
