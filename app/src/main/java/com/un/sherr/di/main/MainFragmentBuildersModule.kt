package com.un.sherr.di.main

import com.un.sherr.di.main.addgood.AddGoodVMModule
import com.un.sherr.di.main.authorization.AuthorizationVMModule
import com.un.sherr.di.main.chats.ChatDirectVMModule
import com.un.sherr.di.main.chats.ChatVMModule
import com.un.sherr.di.main.favorites.FavoritesVMModule
import com.un.sherr.di.main.main.MainVMModule
import com.un.sherr.di.main.offer.DetailOfferVMModule
import com.un.sherr.di.main.profile.ProfileVMModule
import com.un.sherr.ui.addgood.AddGood2Fragment
import com.un.sherr.ui.addgood.AddGoodFragment
import com.un.sherr.ui.addgood.CameraFragment
import com.un.sherr.ui.addgood.PoliciesFragment
import com.un.sherr.ui.authorization.AuthorizationFragment
import com.un.sherr.ui.authorization.RegistrationFragment
import com.un.sherr.ui.chat.ChatDirectFragment
import com.un.sherr.ui.chat.ChatsFragment
import com.un.sherr.ui.favorites.FavoritesFragment
import com.un.sherr.ui.main.MainFilterFragment
import com.un.sherr.ui.main.MainFragment
import com.un.sherr.ui.main.filters.*
import com.un.sherr.ui.offer.*
import com.un.sherr.ui.payment.CardFragment
import com.un.sherr.ui.payment.PayFragment
import com.un.sherr.ui.profile.*
import com.un.sherr.ui.profile.comments.CommentsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector(modules = [MainVMModule::class])
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector(modules = [MainVMModule::class])
    abstract fun contributeMainFilterFragment(): MainFilterFragment

    @ContributesAndroidInjector(modules = [MainVMModule::class])
    abstract fun contributeSelectPositionMapFragment(): SelectPositionMapFragment

    @ContributesAndroidInjector(modules = [MainVMModule::class])
    abstract fun contributeDistanceFragment(): DistanceFragment

    @ContributesAndroidInjector(modules = [MainVMModule::class])
    abstract fun contributeSelectLocationFragment(): SelectLocationFragment

    @ContributesAndroidInjector(modules = [MainVMModule::class])
    abstract fun contributeRubricFragment(): RubricFragment

    @ContributesAndroidInjector(modules = [DetailOfferVMModule::class])
    abstract fun contributeDetailOfferFragment(): DetailOfferFragment

    @ContributesAndroidInjector(modules = [ProfileVMModule::class])
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector(modules = [ProfileVMModule::class])
    abstract fun contributeMyProfileFragment(): MyProfileFragment

    @ContributesAndroidInjector(modules = [ProfileVMModule::class])
    abstract fun contributeVerifyFragment(): VerifyFragment

    @ContributesAndroidInjector(modules = [ProfileVMModule::class])
    abstract fun contributeCommentsFragment(): CommentsFragment

    @ContributesAndroidInjector(modules = [ChatVMModule::class])
    abstract fun contributeChatsFragment(): ChatsFragment

    @ContributesAndroidInjector(modules = [ChatDirectVMModule::class])
    abstract fun contributeChatDirectFragment(): ChatDirectFragment

    @ContributesAndroidInjector
    abstract fun contributePictureFragment(): PictureFragment

    @ContributesAndroidInjector
    abstract fun contributePoliciesFragment(): PoliciesFragment

    @ContributesAndroidInjector
    abstract fun contributeFullPictureFragment(): FullPictureFragment

    @ContributesAndroidInjector
    abstract fun contributeLeaseOfferFragment(): LeaseOfferFragment

    @ContributesAndroidInjector
    abstract fun contributeCardFragment(): CardFragment

    @ContributesAndroidInjector
    abstract fun contributePayFragment(): PayFragment

    @ContributesAndroidInjector
    abstract fun contributeCreateCommentFragment(): CreateCommentFragment

    @ContributesAndroidInjector(modules = [AddGoodVMModule::class])
    abstract fun contributeAddGoodFragment(): AddGoodFragment

    @ContributesAndroidInjector(modules = [AddGoodVMModule::class])
    abstract fun contributeAddGood2Fragment(): AddGood2Fragment

    @ContributesAndroidInjector(modules = [AddGoodVMModule::class])
    abstract fun contributeCameraFragment(): CameraFragment

    @ContributesAndroidInjector(modules = [FavoritesVMModule::class])
    abstract fun contributeFavoritesFragment(): FavoritesFragment

    @ContributesAndroidInjector(modules = [FavoritesVMModule::class])
    abstract fun contributeArchivesFragment(): ArchivesFragment

    @ContributesAndroidInjector(modules = [FavoritesVMModule::class])
    abstract fun contributeActivesFragment(): ActivesFragment

    @ContributesAndroidInjector(modules = [FavoritesVMModule::class])
    abstract fun contributeDueRentFragment(): DueRentFragment

    @ContributesAndroidInjector(modules = [AuthorizationVMModule::class])
    abstract fun contributeAuthorizationFragment(): AuthorizationFragment

    @ContributesAndroidInjector(modules = [AuthorizationVMModule::class])
    abstract fun contributeRegistrationFragment(): RegistrationFragment

    @ContributesAndroidInjector(modules = [FavoritesVMModule::class])
    abstract fun contributeSubcategoryFragment(): SubcategoryFragment
}
