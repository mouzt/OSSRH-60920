namespace java com.meituan.mzt.thrift

/**
 * @InterfaceDoc(
 *     type = "octo.thrift",
 *     displayName = "用户服务",
 *     description = "用户服务",
 *     scenarios = ""
 * )
 */
service UserThriftService {
    /**
     * @MethodDoc(
     *     displayName = "更新用户信息",
     *     description = "更新用户信息",
     *     parameters = {
     *         @ParamDoc( name = "misId", description = "用户misId", example = "xxx"),
     *         @ParamDoc( name = "name", description = "用户名", example = "张三")
     *     },
     *     returnValueDescription = "更新结果",
     *     extensions = {
     *         @ExtensionDoc(name = "SECURITY_PRIVILEGE", content = "不需要鉴权")
     *     }
     * )
    */
    bool updateUserInfo(string misId, string name);
}