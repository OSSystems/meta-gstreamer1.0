SUMMARY  = "The SPIR-V Tools project provides an API and commands for \
processing SPIR-V modules"
DESCRIPTION = "The project includes an assembler, binary module parser, \
disassembler, validator, and optimizer for SPIR-V."
SECTION = "graphics"
LICENSE  = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV = "b27b1afd12d05bf238ac7368bb49de73cd620a8e"
SRC_URI = "git://github.com/KhronosGroup/SPIRV-Tools.git"
UPSTREAM_CHECK_GITTAGREGEX = "^(?P<pver>\d+(\.\d+)+)$"
S = "${WORKDIR}/git"

inherit cmake python3native

DEPENDS = "spirv-headers"

EXTRA_OECMAKE += "-DSPIRV-Headers_SOURCE_DIR=${STAGING_EXECPREFIXDIR}"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

PACKAGES =+ "${PN}-lesspipe"
FILES_${PN}-lesspipe = "${base_bindir}/spirv-lesspipe.sh"
RDEPENDS_${PN}-lesspipe += "${PN} bash"

BBCLASSEXTEND = "native nativesdk"
