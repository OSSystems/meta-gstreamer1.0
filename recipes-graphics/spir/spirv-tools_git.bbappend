
do_install_append() {
    install -d ${D}/${includedir}/spirv/unified1
    install -m 0644 ${DEST_DIR}/spirv-headers/include/spirv/unified1/* ${D}/${includedir}/spirv/unified1
}

BBCLASSEXTEND = "native nativesdk"
