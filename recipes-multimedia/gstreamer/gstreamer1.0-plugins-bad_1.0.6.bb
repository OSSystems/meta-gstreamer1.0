require gstreamer1.0-plugins.inc

LICENSE = "GPLv2+ & LGPLv2+ & LGPLv2.1+ "
LIC_FILES_CHKSUM = "file://COPYING;md5=73a5855a8119deb017f5f13cf327095d \
                    file://gst/tta/filters.h;beginline=12;endline=29;md5=8a08270656f2f8ad7bb3655b83138e5a \
                    file://COPYING.LIB;md5=21682e4e8fea52413fd26c60acb907e5 \
                    file://gst/tta/crc32.h;beginline=12;endline=29;md5=71a904d99ce7ae0c1cf129891b98145c"

DEPENDS += "gstreamer1.0-plugins-base bzip2"

SRC_URI[md5sum] = "5fe353942c6e5adac23f35542b131484"
SRC_URI[sha256sum] = "92130899d0b78b71f1551cada9b10b550e91506c2d7b8b748e5cc18a620d302d"

PR = "r1"

S = "${WORKDIR}/gst-plugins-bad-${PV}"

inherit gettext


PACKAGECONFIG ??= " \
    curl eglgles \
    "

PACKAGECONFIG[assrender] = "--enable-assrender,--disable-assrender,libass"
PACKAGECONFIG[curl]      = "--enable-curl,--disable-curl,curl"
PACKAGECONFIG[eglgles]   = "--enable-eglgles,--disable-eglgles,virtual/egl virtual/libgles2"
PACKAGECONFIG[faad]      = "--enable-faad,--disable-faad,faad2"
PACKAGECONFIG[libmms]    = "--enable-libmms,--disable-libmms,libmms"
PACKAGECONFIG[modplug]   = "--enable-modplug,--disable-modplug,libmodplug"
PACKAGECONFIG[mpg123]    = "--enable-mpg123,--disable-mpg123,mpg123"
PACKAGECONFIG[opus]      = "--enable-opus,--disable-opus,libopus"
PACKAGECONFIG[flite]     = "--enable-flite,--disable-flite,flite-alsa"
PACKAGECONFIG[opencv]    = "--enable-opencv,--disable-opencv,opencv"



# these plugins have not been ported to 1.0 (yet):
#   directdraw vcd uvch264 apexsink cdaudio cog dc1394 directfb dirac fbdev jasper
#   kate ladspa lv2 linsys musepack musicbrainz mythtv nas neon ofa openal rsvg
#   timidity teletextdec wildmidi sdl sndfile xvid wininet acm gsettings sndio

EXTRA_OECONF += " \
    --enable-dvb \
    --enable-shm \
    --disable-uvch264 \
    --disable-vcd \
    --disable-fbdev \
    --disable-examples \
    --disable-experimental \
    --disable-celt \
    --disable-chromaprint \
    --disable-decklink \
    --disable-dts \
    --disable-faac \
    --disable-gme \
    --disable-gsm \
    --disable-mpeg2enc \
    --disable-mplex \
    --disable-resindvd \
    --disable-rtmp \
    --disable-schro \
    --disable-soundtouch \
    --disable-spandsp \
    --disable-vdpau \
    --disable-wayland \
    --disable-acm \
    --disable-android_media \
    --disable-apexsink \
    --disable-apple_media \
    --disable-avc \
    --disable-cdaudio \
    --disable-cog \
    --disable-dc1394 \
    --disable-dirac \
    --disable-direct3d \
    --disable-direct3d9 \
    --disable-directdraw \
    --disable-directsound \
    --disable-directshow \
    --disable-gsettings \
    --disable-kate \
    --disable-ladspa \
    --disable-linsys \
    --disable-lv2 \
    --disable-mimic \
    --disable-musepack \
    --disable-mythtv \
    --disable-nas \
    --disable-ofa \
    --disable-osx_video \
    --disable-pvr \
    --disable-quicktime \
    --disable-sndio \
    --disable-spc \
    --disable-swfdec \
    --disable-teletextdec \
    --disable-timidity \
    --disable-voaacenc \
    --disable-voamrwbenc \
    --disable-wildmidi \
    --disable-wininet \
    --disable-xvid \
    --disable-zbar \
    ${GSTREAMER_1_0_ORC} \
    "

ARM_INSTRUCTION_SET = "arm"

do_configure_prepend() {
	# This m4 file contains nastiness which conflicts with libtool 2.2.2
	rm ${S}/m4/lib-link.m4 || true
}
