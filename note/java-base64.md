```java

public static void main(String[] args) throws UnsupportedEncodingException {
        Base64 base64 = new Base64();
        byte[] bytes = "abcdefg".getBytes();
        byte[] bytes1 = "abcdefg".getBytes("UTF-8");
        String base64Img = new String(base64.encode(bytes));
        String base64Img1 = new String(base64.encode(bytes1));
        String s = new String(base64.decode(base64Img));
        String s1 = new String(base64.decode(base64Img1));
        String s2 = new String(base64.decode(base64Img1), "GBK");
        System.out.println();

    }

```